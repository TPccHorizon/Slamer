#!/bin/bash
set -e

/etc/init.d/postgresql start
sleep 5
psql -f init.sql
/etc/init.d/postgresql stop