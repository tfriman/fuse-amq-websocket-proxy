#!/usr/bin/env bash
# Stops nginx

rundir=$(dirname "$0")
nginx -p $rundir -c ws.conf -s stop