<head>
<meta http-equiv="Content-Type" content="text/html;">
<style>
	body {
		width: 100%;
	}

    <!--
	.template-title {
		font-family: 굴림,Verdana;
		color: #6B6F43;
		font-weight: bold;
		background-color: #F3F2EB;
		display: flex;
		justify-content: center;
		align-items: center;
	}

	.template-title h1 {
	    margin: 0;
	}

	.div-margin {
		height: 100px;
	}
	-->

	.template-contents table {
		width: 100%;
		border-spacing: 0;
		padding: 0;
		border-collapse: collapse;
		text-align: center;
		line-height: 24px;
	}

	.template-contents table th {
		border: 1px solid;
		border-color: #C8C8C8;
		padding: 2px;
		background-color: #E5E5E5;
	}

	.template-contents table td {
		border: 1px solid;
		border-color: #C8C8C8;
		padding: 2px;
		text-align: left;
		padding-left: 5px;
	}

	.div-link {
		font-family: 굴림,Verdana;
		font-size: 18px;
		font-weight: bold;
		display: flex;
		justify-content: center;
		align-items: center;
	}

	.div-link a {
        width: 150px;
        text-decoration: none;
        background-color: #efefef;
        text-align: center;
        padding: 10px;
        color: #000000;
	}

	.div-link a:hover, a:active {
        color: #007bff;
	}
	
	.template-contents table {
        width: 100%;
        border-spacing: 0;
        padding: 0;
        border-collapse: collapse;
        text-align: center;
        line-height: 24px;
        font-size:12px;
        color:#606266;
    }
</style>
</head>
<body>
    <!--
    <div class="template-title">
	    <h2>${title}</h2>
	</div>
	<div class="div-margin"></div>
	-->
	<div class="template-contents">
	    ${contents}
	</div>
	<br/>
	<br/>
	<div class="div-link">
		<a href="${link}" target="_blank" onclick="window.open(this.href, '_blank', 'width=1000, height=800, resizable=no, scrollbars=no'); return false;">
		    상세보기
		</a>
	</div>
</body>