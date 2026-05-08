#!/bin/sh
mongoimport --db medilabo_report_db --collection report --file /docker-entrypoint-initdb.d/medilabo_report_db.report.json --jsonArray