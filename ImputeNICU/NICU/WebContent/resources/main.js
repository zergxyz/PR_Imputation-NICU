var formData = {};

function handleMSForm(e) {
        var next = "";
        
        //gather the fields
        var data = $(this).serializeArray();

        //store them - assumes unique names
        for(var i=0; i<data.length; i++) {
            //If nextStep, it's our metadata, don't store it in formdata
            if(data[i].name=="nextStep") { next=data[i].value; continue; }
            //if we have it, add it to a list. This is not "comma" safe.
            if(formData.hasOwnProperty(data[i].name)) formData[data[i].name] += ","+data[i].value;
            else formData[data[i].name] = data[i].value;
        }

        //now - we need to go the next page...
        //if next step isn't a full url, we assume internal link
        //logic will be, if something.something, do a post
        if(next.indexOf(".") == -1) {
            var nextPage = "#" + next;
            $.mobile.changePage(nextPage);
        } else {
            $.mobile.changePage(next, {type:"post",data:formData});
        }
        e.preventDefault();
    
};