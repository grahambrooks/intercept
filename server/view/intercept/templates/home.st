<html>
<head>
    <title>Intercept - Server</title>
    <link href="intercept.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="content">
    <div class="title">Intercept dynamic proxy configuration</div>
    <a class="back" href="/doc/index.html">Intercept documentation</a>
    <div class="content">
        <div class="status">Intercept is currently running on port $port$</div>
    </div>

    <div class="subtitle">Currently configured proxy servers</div>
    <ul>
        $proxies: {
        <li><a href="/$it.name$">$it.name$</a></li>
        }$
    </ul>
    <a href="/proxy/new">New Proxy</a>
</div>
</body>
</html>
