echo
echo  stop all container...
echo
docker-compose down

echo
echo  build all modules...
echo
mvn clean package

echo
echo  build container...
echo
docker-compose build

echo
echo  start container...
echo
docker-compose up -d --remove-orphans

echo
echo  poll kibana server...
echo
curl_status=404
while [ "$curl_status" -ne "200" ]
do
  echo "poll http://localhost:5601/app/kibana until kibana is up..."
  curl_status=$(curl -s -o /dev/null -w '%{http_code}' --connect-timeout 3 http://localhost:5601/app/kibana)
  sleep 2
done

echo
echo  POST failing request...
echo
response=$(curl -s -X POST -d '{"sku": "1234", "qty": 2000}'  -H 'Content-Type: application/json' http://localhost:8082/order)
traceId=$(echo $response | jq -r '.traceId')
echo $response
echo $traceId

echo
echo  open kibana in  Chrome...
echo
kibana="http://localhost:5601/app/kibana#/discover?_g=(refreshInterval:(pause:!f,value:5000),time:(from:now-1y,mode:quick,to:now))&_a=(columns:!(appName,traceId,spanId,message,level),index:'8230ebe0-c9b1-11ea-8f45-f77d0c95f0d4',interval:auto,query:(language:lucene,query:'$traceId'),sort:!('@timestamp',desc))"
echo $kibana
/Applications/Google\ Chrome.app/Contents/MacOS/Google\ Chrome $kibana

echo
echo stop all container...
echo
read -rsp $'Press any key to continue stop container\n' -n1 key
docker-compose down

