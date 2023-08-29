axios.defaults;

function getInputValue(id) {
  return document.getElementById(id).value;
}
function setValue(id, v) {
  document.getElementById(id).value = v;
}

function setValueFinalUrl() {
  let fv = "";
  let url = getInputValue("url");
  fv += url;
  setValue("finalUrl", fv);
  fv = "/";
  let resortID = getInputValue("resortID");
  fv += resortID;
  let seasonID = getInputValue("seasonID");
  fv += "/seasons/" + seasonID;
  let dayID = getInputValue("dayID");
  fv += "/days/" + dayID;
  let skierID = getInputValue("skierID");
  fv += "/skiers/" + skierID;
  setValue("api", fv);
}

function setValueFinalUrlGet() {
  let fv = "";
  let url = getInputValue("urlGet");
  fv += url;
  setValue("finalUrlGet", fv);
  fv = "/";
  let resortID = getInputValue("resortIDGet");
  fv += resortID;
  let seasonID = getInputValue("seasonIDGet");
  fv += "/seasons/" + seasonID;
  let dayID = getInputValue("dayIDGet");
  fv += "/days/" + dayID;
  let skierID = getInputValue("skierIDGet");
  fv += "/skiers/" + skierID;
  setValue("apiGet", fv);
}

setValueFinalUrl();
setValueFinalUrlGet();

document.getElementById("url").addEventListener("keyup", function (e) {
  setValueFinalUrl();
});

document.getElementById("resortID").addEventListener("keyup", function (e) {
  setValueFinalUrl();
});

document.getElementById("seasonID").addEventListener("keyup", function (e) {
  setValueFinalUrl();
});

document.getElementById("dayID").addEventListener("keyup", function (e) {
  setValueFinalUrl();
});

document.getElementById("skierID").addEventListener("keyup", function (e) {
  setValueFinalUrl();
});

// get
document.getElementById("urlGet").addEventListener("keyup", function (e) {
  setValueFinalUrlGet();
});

document.getElementById("resortIDGet").addEventListener("keyup", function (e) {
  setValueFinalUrlGet();
});

document.getElementById("seasonIDGet").addEventListener("keyup", function (e) {
  setValueFinalUrlGet();
});

document.getElementById("dayIDGet").addEventListener("keyup", function (e) {
  setValueFinalUrlGet();
});

document.getElementById("skierIDGet").addEventListener("keyup", function (e) {
  setValueFinalUrlGet();
});

function putOutput(msg) {
  document.getElementById("output").innerHTML = msg;
}

function putOutputGet(msg) {
  document.getElementById("outputGet").innerHTML = msg;
}

function getRandomInt(min, max) {
  min = Math.ceil(min);
  max = Math.floor(max);
  return Math.floor(Math.random() * (max - min) + min); // The maximum is exclusive and the minimum is inclusive
}

var count = 0;
document.getElementById("sendPost").addEventListener("click", function (e) {
  let data = getInputValue("body").replace("\r", "").replace("\n", "");
  let url = getInputValue("finalUrl") + getInputValue("api");
  console.log(url);
  let conf = {
    method: "post",
    url: url,
    data: data,
    headers: {
      "Content-Type": "application/json",
    },
  };
  axios(conf)
    .then(function (response) {
      // alert(response);
      putOutput(
        "Response Code: " +
          response.status +
          "</br></br>" +
          response.data.message
      );
    })
    .catch(function (error) {
      if (error.message === "Network Error") {
        putOutput(error.message + "</br></br>Make sure the URL is accessable.");
      } else {
        putOutput(error.message + "</br></br>" + error.response.data.message);
      }
      console.error(error);
    })
    .then(function () {
      setValue("resortID", getRandomInt(0, 10000));
      setValue("seasonID", getRandomInt(1, 4));
      setValue("dayID", getRandomInt(1, 30));
      setValue("skierID", getRandomInt(0, 10000000));
      setValue(
        "body",
        `{\r\n    "time": ${getRandomInt(
          0,
          1000
        )},\r\n    "liftID": ${getRandomInt(0, 100)}\r\n}`
      );
      document.getElementById("count").innerHTML =
        "Response Or Error Messages (current request of this session: " +
        ++count +
        ")";
    });
});

var countGet = 0;
document.getElementById("sendGet").addEventListener("click", function (e) {
  let url = getInputValue("finalUrlGet") + getInputValue("apiGet");
  console.log(url);
  let conf = {
    method: "get",
    url: url,
    headers: {
      "Content-Type": "application/json",
    },
  };
  axios(conf)
    .then(function (response) {
      // alert(response);
      console.log(response);
      putOutputGet(
        "Response Code: " + response.status + "</br></br>" + response.data
      );
    })
    .catch(function (error) {
      if (error.message === "Network Error") {
        putOutputGet(
          error.message + "</br></br>Make sure the URL is accessable."
        );
      } else {
        putOutputGet(
          error.message + "</br></br>" + error.response.data.message
        );
      }
      console.error(error);
    })
    .then(function () {
      setValue("resortIDGet", getRandomInt(0, 10000));
      setValue("seasonIDGet", getRandomInt(1, 4));
      setValue("dayIDGet", getRandomInt(1, 30));
      setValue("skierIDGet", getRandomInt(0, 10000000));
      document.getElementById("countGet").innerHTML =
        "Response Or Error Messages (current request of this session: " +
        ++countGet +
        ")";
    });
});
