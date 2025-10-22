import os
from flask import Flask

app = Flask(__name__)

# define a helloworld RESTful endpoint


@app.route("/", methods=['GET', 'POST'])
def hello_world():
    """Example Hello World route."""

    return f"Helloddasd !!!"


# start the flask web server
if __name__ == "__main__":
    app.run(debug=True, host="0.0.0.0", port=int(os.environ.get("PORT", 8080)))
