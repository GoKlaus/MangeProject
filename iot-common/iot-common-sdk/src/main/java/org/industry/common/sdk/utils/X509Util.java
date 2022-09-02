package org.industry.common.sdk.utils;

import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMReader;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.Security;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;

@Slf4j
public class X509Util {
    public static SSLSocketFactory getSSLSocketFactory(final String caCrtFile, final String crtFile, final String keyFile, final String password) {
        try {
            Security.addProvider(new BouncyCastleProvider());

            //load CA certification
            X509Certificate caCert = loadCerticate(caCrtFile);
            // CA certificate is used to authenticate server
            KeyStore caKeyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            caKeyStore.load(null, null);
            caKeyStore.setCertificateEntry("cacertfile", caCert);
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(caKeyStore);

            //load client certificate
            X509Certificate cert = loadCerticate(crtFile);
            // load client private key
            KeyPair key = loadCerticateWithPassword(keyFile, password);
            // load key and certificates are sent to server so it can authenticate us
            KeyStore certKeyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            certKeyStore.load(null, null);
            certKeyStore.setCertificateEntry("certfile", cert);
            certKeyStore.setKeyEntry("keyfile", key.getPrivate(), password.toCharArray(), new Certificate[]{cert});
            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            keyManagerFactory.init(certKeyStore, password.toCharArray());

            // finally create SSL socket factory
            SSLContext context = SSLContext.getInstance("TLSv1.2");
            context.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), null);
            return context.getSocketFactory();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static <T> T loadCerticate(String caCrtFile) throws IOException {
        return loadCerticateWithPassword(caCrtFile, null);
    }

    @SuppressWarnings("all")
    private static <T> T loadCerticateWithPassword(String caCrtFile, String password) throws IOException {
        PEMReader reader = null;
        try {
            String classpath = "classpath:";
            if (caCrtFile.startsWith(classpath)) {
                reader = null != password ? new PEMReader(new InputStreamReader(X509Util.class.getResourceAsStream(caCrtFile.replace(classpath, ""))), password::toCharArray)
                        : new PEMReader(new InputStreamReader(X509Util.class.getResourceAsStream(caCrtFile.replace(classpath, ""))));
            } else {
                reader = null != password ? new PEMReader(new InputStreamReader(new ByteArrayInputStream(Files.readAllBytes(Paths.get(caCrtFile)))), password::toCharArray)
                        : new PEMReader(new InputStreamReader(new ByteArrayInputStream(Files.readAllBytes(Paths.get(caCrtFile)))));
            }
            return (T) reader.readObject();
        } finally {
            if (null != reader) {
                reader.close();
            }
        }
    }
}
