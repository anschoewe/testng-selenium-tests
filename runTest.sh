#!/bin/bash

rm -fr acceptance-test-results.txt

docker run --rm -it --name testng-selenium-tests testng-selenium-tests > acceptance-test-results.txt

if grep -q Failed "acceptance-test-results.txt"; then
  echo "Failed"
  exit(1)
else
  echo "Passed"
  exit(0)
fi
