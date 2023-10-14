const express = require('express');
const fs = require('fs');
const path = require('path');
const app = express();

let title = "Black Clover";
let chapter = 123;
let page = 1;
const port = 8000

getUri = (t, c, p, url) => url? `localhost:${port}/${t}/${t} ${c}/${p}.mp4` : `${t}/${t} ${c}/${p}.mp4`

function pageExists(title, chapter, page) {
	const uri = getUri(title, chapter, page);
	let exists = false;
	
	try {
		exists = fs.existsSync(path.resolve(__dirname, 'public', uri));
	} catch (e) {
		console.error('idk mann :' + e.message);
	}
	
	return exists;
}

function setToNextChapterAndGetFirstPage() {
	chapter++;
	page = 1;
	return getUri(title, chapter, page, true);
}

function getThisPageAndSetToNextPage() {
	const uri = getUri(title, chapter, page, true);
	page++;
	return uri;
}

app.use(express.static('public'));

app.use(function(req, res, next) {
  res.header("Access-Control-Allow-Origin", "*"); 
  res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
  next();
});


app.get('/', (req, res) => {
	if(pageExists(title, chapter, page)) {
		return res.send(getThisPageAndSetToNextPage())
	} else {
		return res.send(setToNextChapterAndGetFirstPage())
	}
});



app.listen(port, () => console.log('listening on port '+port));
