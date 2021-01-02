// MongoDB playground

// create message collection

DBQuery.shellBatchSize = 1000000;



db.Account.find();

db.Route.createIndex({supplier: 1});
db.Airplane.createIndex({supplierId: 1});
db.Ticket.createIndex({flightId: 1});

db.Airplane.getIndexes()
db.Route.getIndexes()
