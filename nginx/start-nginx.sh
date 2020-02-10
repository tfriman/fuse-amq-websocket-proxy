#!/usr/bin/env bash
# Starts nginx

rundir=$(dirname "$0")
nginx -p $rundir -c ws.conf