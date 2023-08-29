import os

from flask import Flask, request

app = Flask(__name__)


@app.route("/")
def hello_world():
    """Example Hello World route."""

    print("Example Hello World route.")
    print(request.method)
    
    return f"Hello World!!!@@@!!!"


if __name__ == "__main__":
    app.run(debug=True, host="0.0.0.0", port=int(os.environ.get("PORT", 8080)))
