// MongoDB playground

// create message collection

DBQuery.shellBatchSize = 1000000;

db.Account.find();

db.Route.createIndex({supplier: 1});
db.Airplane.createIndex({supplierId: 1});
db.Ticket.createIndex({flightId: 1});
db.Ticket.createIndex({flightId: 1, seatType: 1});
db.Flight.createIndex({isExpired: 1, supplierId: 1, airlineStart: 1, airlineEnd: 1});
db.Flight.createIndex({isExpired: 1, airlineStart: 1, airlineEnd: 1});
db.Flight.createIndex({isExpired: 1})
db.Airplane.getIndexes()
db.Route.getIndexes()
db.Flight.getIndexes()
db.Ticket.getIndexes()
