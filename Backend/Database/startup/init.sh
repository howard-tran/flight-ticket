#!/bin/sh
  screen -d -m mongod \
  && sleep 1 && mongo bet_store mongo-init.js \
  && screen -r;