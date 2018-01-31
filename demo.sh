#!/bin/sh

set -x

curl -q http://localhost:8080/listAll 2>/dev/null|python -m json.tool

curl -q http://localhost:8080/showProduct 2>/dev/null|python -m json.tool

curl -q http://localhost:8080/showProduct/XYZ 2>/dev/null|python -m json.tool

curl -q http://localhost:8080/showProduct/TO-02 2>/dev/null|python -m json.tool

curl -q http://localhost:8080/showPrice 2>/dev/null|python -m json.tool

curl -q http://localhost:8080/showPrice/XYZ 2>/dev/null|python -m json.tool

curl -q http://localhost:8080/showPrice/XYZ/BLA 2>/dev/null|python -m json.tool

curl -q http://localhost:8080/showPrice/TO-02/BLA 2>/dev/null|python -m json.tool

curl -q http://localhost:8080/showPrice/XYZ/piece 2>/dev/null|python -m json.tool

curl -q http://localhost:8080/showPrice/TO-02/piece 2>/dev/null|python -m json.tool

exit 0
