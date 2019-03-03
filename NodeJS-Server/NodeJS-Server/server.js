var net = require('net');   //net modülü ile TCP bağlantısı yapılacak.
var server = net.createServer();
var readline = require('readline');

const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout
});

server.on("connection", function (socket) {
    var remoteAdress = socket.remoteAddress + ":" + socket.remotePort;
    console.log("new client is node %s", remoteAdress);

    socket.on("data", function (d) {    //Veri alındığında çalışır.
        console.log("Data from %s : %s", remoteAdress, d);
    });

    socket.once("close", function () {
        console.log("Connection from %s closed", remoteAdress);
    });

    socket.on("error", function (err) {
        console.log("Connection %s error : % s", remoteAdress, err.message);
    });
    
    rl.on('line', (input) => {
        socket.write(input);
    });
});

server.listen(3000, function () {
    console.log("server listening to %j", server.address());
});

