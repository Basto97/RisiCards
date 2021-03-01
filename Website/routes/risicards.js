const express = require('express');
const router = express.Router();
const path = require('path');
const jsonfile = require('jsonfile');
const fs = require('fs');
const auth = require('../auth')


router.get('/data', function(req, res, next) {
    res.setHeader('Content-Type', 'application/json')
    let file = path.normalize(__dirname + '/' + '../risicards/data.json');
    jsonfile.readFile(file, function(err, obj) {
        if(err) {
            res.json({status: 'error', reason: err.toString()});
            return;
        }
        res.json(obj);
    });
});

router.get('/commands', auth, function(req, res, next) {
    const fileName = __dirname + '/' + '../risicards/commands.json';
    let file = path.normalize(fileName);
    jsonfile.readFile(file, function(err, obj) {
        res.setHeader('Content-Type', 'application/json')
        if(err) {
            res.json({status: 'error', reason: err.toString()});
            return;
        }
        res.json(obj);
    });

    const fileR = require(fileName);
    fileR.refresh = false;
    fs.writeFile(fileName, JSON.stringify(fileR, null, 2), function writeJSON(err) {  });
});

module.exports = router;