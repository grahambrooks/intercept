<html>
<head>
    <title>New Proxy</title>
    <link rel="stylesheet" type="text/css" href="/intercept.css"/>
</head>
<body>
<div class="content">
    <div class="title">Create a new proxy server</div>
    <form action="/proxy/new" method="POST" name="create-proxy">
        <fieldset>
            <label for="proxy-name">Proxy Name</label>
            <input type="text" id="proxy-name" name="proxy-name"/>
            <label for="proxy-port">Proxy Port</label>
            <input type="text" id="proxy-port" name="proxy-port"/>
        </fieldset>
        <button type="submit">Create Proxy</button>
    </form>
</div>
</body>
</html>
