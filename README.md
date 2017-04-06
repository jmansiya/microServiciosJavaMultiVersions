# microServiciosJavaMultiVersions
Repositorio para ejemplo de despiegue de microservicios multiversión y utilización de RIBBON para llamadas a microservicios.

Proyecto: demo-microservicio-multiversion-api-1. Este proyecto tendrá dos versiones y mediante eureka.instance.metadata-map.versions: v1,v2  podremos decidir en el servidor llamador de este microservicio que versión utilizar, de esta forma las versiones anteriores no dejan de ser útiles.

Proyecto: demo-multiversion-registration-api-2: Proyecto que utilizará Ribbon para realizar la llamadas mediante RestTemplate, implementará el servicio: DefaultNIWSServerListFilter para devolver el listado de servicios que están disponibles para una petición determinada.

Ejemplo de funcionamiento de listado de servicios disponibles:

2017-04-05 18:08:45.953  INFO 12096 --- [nio-8700-exec-1] c.n.l.DynamicServerListLoadBalancer      : DynamicServerListLoadBalancer for client demo-multiversion-registration-api1-v1 initialized: DynamicServerListLoadBalancer:{NFLoadBalancer:name=demo-multiversion-registration-api1-v1,current list of Servers=[10.208.74.45:8601, 10.208.74.45:8602],Load balancer stats=Zone stats: {defaultzone=[Zone:defaultzone;	Instance count:2;	Active connections count: 0;	Circuit breaker tripped count: 0;	Active connections per server: 0.0;]
},Server stats: [[Server:10.208.74.45:8601;	Zone:defaultZone;	Total Requests:0;	Successive connection failure:0;	Total blackout seconds:0;	Last connection made:Thu Jan 01 01:00:00 CET 1970;	First connection made: Thu Jan 01 01:00:00 CET 1970;	Active Connections:0;	total failure count in last (1000) msecs:0;	average resp time:0.0;	90 percentile resp time:0.0;	95 percentile resp time:0.0;	min resp time:0.0;	max resp time:0.0;	stddev resp time:0.0]
, [Server:10.208.74.45:8602;	Zone:defaultZone;	Total Requests:0;	Successive connection failure:0;	Total blackout seconds:0;	Last connection made:Thu Jan 01 01:00:00 CET 1970;	First connection made: Thu Jan 01 01:00:00 CET 1970;	Active Connections:0;	total failure count in last (1000) msecs:0;	average resp time:0.0;	90 percentile resp time:0.0;	95 percentile resp time:0.0;	min resp time:0.0;	max resp time:0.0;	stddev resp time:0.0]
]}ServerList:DiscoveryEnabledNIWSServerList:; clientName:demo-multiversion-registration-api1-v1; Effective vipAddresses:demo-multiversion-registration-api-1; isSecure:false; datacenter:null
2017-04-05 18:09:11.955  INFO 12096 --- [erListUpdater-0] c.netflix.config.ChainedDynamicProperty  : Flipping property: demo-multiversion-registration-api1-v1.ribbon.ActiveConnectionsLimit to use NEXT property: niws.loadbalancer.availabilityFilteringRule.activeConnectionsLimit = 2147483647

Como se puede apreciar en los logs se evalua dinámicamente el numero de servicios que están disponibles.

