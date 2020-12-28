// MongoDB playground

// create message collection

DBQuery.shellBatchSize = 1000000;



db.Account.find();

db.Ticket.createIndex({airlineStart: 1, airlineEnd: 1});
db.Ticket.createIndex({airlineStart: 1});
db.Ticket.createIndex({airlineEnd: 1});
db.Ticket.createIndex({agencyId: 1})

db.Supplier.insertMany([
  {"name":"VietnamAirLine", "countryCode": "VN"},
  {"name":"BambooAirline", "countryCode": "VN"}
]);