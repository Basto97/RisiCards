const express = require('express');
const router = express.Router();
const path = require('path');
const jsonfile = require('jsonfile');


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

module.exports = router;