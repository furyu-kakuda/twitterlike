#!/bin/sh


cd front
npm run build
cd ../
sbt run
