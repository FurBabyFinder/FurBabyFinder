/**
 * Created by melodytempleton on 7/7/17.
 */

var selection=[];
var maleOnOff = "off";
var femaleOnOff = "off";
var neuterdOnOff = "off";
var pottyOnOff = "off";
var goodWKidsOnOff = "off";
var crateTrainedOnOff = "off";
var greatWDogsOnOff = "off";
var noDogsOnOff = "off";
var catFriendlyOnOff = "off";
var medFosterOnOff = "off";
var microchippedOfOff = "off";
var noCatsOnOff = "off";
var underYearOnOff = "off";


function selectFilter(onOffVar, selectString, id) {
    switch (onOffVar) {
        case "off":
            selection.push(selectString);
            onOffVar = "on";
            $(id).addClass("active");
            return onOffVar;
        case "on":
            var index = selection.indexOf(selectString);
            selection.splice(index, 1);
            $(id).removeClass("active");
            onOffVar = "off";
            return onOffVar;
    }
//       if(selection.length == 0){
//           window.location.href = "/pets/all"
//       }
//       else {
//           window.location.href =  "/pets/" + selection +  "/filterSearch.json";
//       }
}

$("#male").click(function(){
    maleOnOff = selectFilter(maleOnOff, "male", "#male");
});

$("#female").click(function(){
    femaleOnOff = selectFilter(femaleOnOff, "female", "#female");
});


$("#neuterd_spayed").click(function(){
    neuterdOnOff = selectFilter(neuterdOnOff, "neutered_spayed", "#neutered_spayed");
});

$("#potty_trained").click(function(){
    pottyOnOff = selectFilter(pottyOnOff, "potty_trained", "#potty_trained");
});

$("#goodWKids").click(function(){
    goodWKidsOnOff = selectFilter(goodWKidsOnOff, "great_with_kids", "#goodWKids");
});

$("#crateTrained").click(function(){
    crateTrainedOnOff = selectFilter(crateTrainedOnOff, "crate_trained", "#crateTrained");
});

$("#greatWDogs").click(function(){
    greatWDogsOnOff = selectFilter(greatWDogsOnOff, "great_with_dogs", "#greatWDogs");
});

$("#noDogs").click(function(){
    greatWDogsOnOff = selectFilter(noDogsOnOff, "not_dog_friendly", "#noDogs");
});

$("#catFriendly").click(function(){
    catFriendlyOnOff = selectFilter(catFriendlyOnOff, "cat_friendly", "#catFriendly");
});


$("#medicalFoster").click(function(){
    medFosterOnOff = selectFilter(medFosterOnOff, "medical_foster", "#medicalFoster");
});

$("#microchipped").click(function(){
    microchippedOfOff = selectFilter(microchippedOfOff, "microchipped", "#microchipped");
});

$("#noCats").click(function(){
    noCatsOnOff = selectFilter(noCatsOnOff, "not_cat_friendly", "#noCats");
});

$("#underYear").click(function(){
    underYearOnOff = selectFilter(underYearOnOff, "under_year", "#underYear");
});



$("#searchFiltered").click(function(){
    var species = $("#species").val();
    var breed = $("#breed").val();
    if(selection.length == 0){
        window.location.href = "/pets/all"
    }
    else if ((species != "") & (breed != "")) {
        window.location.href =  '/pets/'+ species + '/breed/' + breed +'/' + selection;
    }
    else if (species != "") {
        window.location.href =  "/pets/" + species + "/type/" + selection;
    }
    else {
        window.location.href =  "/pets/" + selection;
    }
});