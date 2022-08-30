db = db.getSiblingDB("admin");
db = db.getSiblingDB("authorization");
if (!db.getUser("admin")) {
    db.createUser({
        user: "admin",
        pwd: "dc3",
        roles: [
            {
                role: "readWrite",
                db: "authorization",
            },
        ],
    });
}

db = db.getSiblingDB("admin");
if (!db.getUser("admin")) {
    db.createUser({
        user: "admin",
        pwd: "dc3",
        roles: [
            {
                role: "readWrite",
                db: "admin",
            },
        ],
    });
}

db = db.getSiblingDB("dc3");
if (!db.getUser("dc3")) {
    db.createUser({
        user: "dc3",
        pwd: "dc3",
        roles: [
            {
                role: "readWrite",
                db: "dc3",
            },
        ],
    });
}

if (db.createCollection("point_value")) {
    db.pointValue.createIndex(
        {
            deviceId: 1,
            pointId: 1,
        },
        {
            name: "IX_device_point_id",
            unique: false,
            background: true,
        }
    );
    db.pointValue.createIndex(
        {
            originTime: 1,
        },
        {
            name: "IX_origin_time",
            unique: false,
            background: true,
        }
    );
}

if (db.createCollection("driver_event")) {
    db.driverEvent.createIndex(
        {
            serviceName: 1,
        },
        {
            name: "IX_service_name",
            unique: false,
            background: true,
        }
    );
    db.driverEvent.createIndex(
        {
            originTime: 1,
        },
        {
            name: "IX_origin_time",
            unique: false,
            background: true,
        }
    );
}

if (db.createCollection("device_event")) {
    db.deviceEvent.createIndex(
        {
            deviceId: 1,
            pointId: 1,
        },
        {
            name: "IX_device_point_id",
            unique: false,
            background: true,
        }
    );
    db.deviceEvent.createIndex(
        {
            originTime: 1,
        },
        {
            name: "IX_origin_time",
            unique: false,
            background: true,
        }
    );
}
