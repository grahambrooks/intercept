grammar Configuration;

@header {
package middleman.configuration;
}

@lexer::header {
package middleman.configuration;
}

configuration[MiddlemanConfiguration config]
	:	(configurationStatement[config])*
	EOF
	;
configurationStatement[MiddlemanConfiguration config]
	:	PORT EQ port=NUMBER { config.setConfigurationPort(Integer.parseInt(port.getText())); }
	|	def=proxyDefinition { config.add(def); }
	;

proxyDefinition returns [ProxyConfig proxy]
@init{ proxy = new ProxyConfig(); }
	:	PROXY name=IDENTIFIER {proxy.setName($name.getText()); }BEGIN (proxyStatement[proxy])* END
	;
	
proxyStatement[ProxyConfig proxy]
	:	PORT '=' port=NUMBER {proxy.setPort(Integer.parseInt($port.getText())); }
	|	ROUTE from=STRING '=>' route=STRING { proxy.addRoute($from.getText(), $route.getText()); }
	|	OUTPROXY '=' outproxy=STRING {proxy.setOutgoingProxy($outproxy.getText());}
	|	statement=stubStatement { proxy.add(statement); }
	|	DEBUG EQ debugLevel=NUMBER {proxy.setDebugLevel(Integer.parseInt($debugLevel.getText())); }
	;	

stubStatement returns[StubResponse stub]
@init {
stub = new StubResponse();
}
	:	STUB url=HOST {$stub.setUrl(url.getText()); }BEGIN
		(stubSetting[stub])*
		END
	;

stubSetting[StubResponse stub]
	:	RESPONSE EQ responseCode=NUMBER { stub.setResponseCode($responseCode.getText()); }
	|	HEADER EQ header=BLOCK {stub.setHeader($header.getText()); }
	|	BODY EQ body=BLOCK {stub.setBody($body.getText()); }
	;
	
PROXY	:	'proxy';
ROUTE	:	'route';
BEGIN	:	'{';
END	:	'}';
STUB	:	'stub';
PORT	:	'port';
RESPONSE:	'response';
HEADER	:	'header';
BODY	:	'body';
OUTPROXY:	'outgoing-proxy';
DEBUG	:	'debug';

EQ	:	'='
	;
	
NUMBER
	:	('0'..'9')+
	;

IDENTIFIER
	:	('a'..'z' | 'A'..'Z' | '_')+
	;
STRING	:	'"' ( '\\' . | ~('\\'|'"') )* '"' 
	;

URL	:	'http://' ('a'..'z' | '0'..'9' | '_' | '-' | '\.' | ':')+
	;

HOST	:	('a'..'z' | '0'..'9' | '_' | '-' | '\.' | ':')+
	;

BLOCK	:	OPENBLOCK (~CLOSEBLOCK)* CLOSEBLOCK
	;
	
	
OPENBLOCK
	:	'['
	;

CLOSEBLOCK
	:	']'
	;

WS	:	(
			' '
		|	'\t'
		|	'\f'
		|	'\n'
		) { skip(); }
	;
