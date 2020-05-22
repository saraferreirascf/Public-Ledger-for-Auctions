# Public-Ledger-for-Auctions
Assignment for CS4030

To run grpc:

mvn exec:java -Dexec.mainClass=HelloWorldClient


// novos comandos para executar
mvn verify
// server
mvn exec:java -D"exec.mainClass"="Public_ledger_Server" -D"exec.args"="13333"
// client
mvn -X exec:java -D"exec.mainClass"="Public_ledger_Client" -D"exec.args"="13333"