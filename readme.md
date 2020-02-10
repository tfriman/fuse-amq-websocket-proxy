# Example how to consume AMQP messages via WebSocket using AMQ 7.4 and Fuse

## Configure local AMQ Broker

Install AMQ Broker v 7.4 to your machine.

Create broker:

```shell script
bin/artemis create cdemo --user foouser --password barpass --allow-anonymous
```

Start it:
```
./cdemo/bin/artemis run
```

Create queue:

```
bin/artemis queue create --auto-create-address --preserve-on-no-consumers --durable --anycast --address testamqp --protocol amqp --name testamqp --url "tcp://localhost:61616"
```

## Configure Nginx to work as a websocket proxy

Install nginx.

There is a example configuration in [nginx/ws.conf](nginx/ws.conf). It listens to 3333 and forwards requests to localhost:5672 (AMQP port) 
You can run it using provided shell scripts:

```
nginx/start-nginx.sh
```

and stop it:
```
nginx/stop-nginx.sh
```

## Run Fuse application sending and receiving messages via Nginx proxy:

```
mvn
```

You should see timer sending messages to queue every 10 seconds and consumer reading them.

## Tested with MacOS 10.15 and Java 8.