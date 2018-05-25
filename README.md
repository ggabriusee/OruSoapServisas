Paleidimas:

docker-compose build


docker-compose up -d


wsdl pasiekiamas: http://localhost:5000/locations/weather.wsdl

SOAP užklausų pavyzdžiai:

GET (all):

<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                  xmlns:wt="com/locations">
   <soapenv:Header/>
   <soapenv:Body>
      <wt:getAllWeatherRequest/>
   </soapenv:Body>
</soapenv:Envelope>

GET (with id):

<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                  xmlns:wt="com/locations">
    <soapenv:Header/>
    <soapenv:Body>
        <wt:getWeatherRequest>
            <wt:id>1</wt:id>
        </wt:getWeatherRequest>
    </soapenv:Body>
</soapenv:Envelope>

POST (create):

<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                  xmlns:wt="com/locations">
  <soapenv:Header/>
  <soapenv:Body>
     <wt:createWeatherRequest>
        <wt:temperature>?</wt:temperature>
        <wt:city>?</wt:city>
        <wt:date>?</wt:date>
     </wt:createWeatherRequest>
  </soapenv:Body>
</soapenv:Envelope>

PUT (update):

<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                  xmlns:wt="com/locations">
   <soapenv:Header/>
   <soapenv:Body>
      <wt:updateWeatherRequest>
         <wt:id>?</wt:id>
         <wt:temperature>?</wt:temperature>
         <wt:city>?</wt:city>
         <wt:date>?</wt:date>
      </wt:updateWeatherRequest>
   </soapenv:Body>
</soapenv:Envelope>

DELETE:

<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                  xmlns:wt="com/locations">
   <soapenv:Header/>
   <soapenv:Body>
      <wt:deleteWeatherRequest>
         <wt:id>?</wt:id>
      </wt:deleteWeatherRequest>
   </soapenv:Body>
</soapenv:Envelope>

