from google.cloud import bigquery
import os
import json
import time

from flask import Flask, request

app = Flask(__name__)


@app.route("/", methods=['GET', 'POST'])
def hello_world():
    """Example Hello World route."""

    return f"Hello World!!! GA NEW"


@app.route("/event_looks", methods=['POST'])
def event_looks():
    print(request.method)
    payload = json.loads(request.data)
    print(payload)

    return "Event Received"


@app.route("/event_receive", methods=['POST'])
def event_receiver():
    payload = json.loads(request.data)

    file_name = payload['name']
    bucket_name = payload['bucket']

    # Construct a BigQuery client object.
    client = bigquery.Client()

    ######################
    # TODO(developer): Set table_id to the ID of the table to create.
    # the format of the table id is:
    # table_id = "your-project.your_dataset.your_table_name"
    # for example, mine was:
    # table_id = f"cloud-tut-397400.cloud_run_tut_dataset.iris"
    table_id = "cloud-tut-437202.cloud_run_tut_dataset.iris"

    ######################
    # BigQuery API for creating a table from a LoadJob
    # create a load job with defined schema and source_format
    job_config = bigquery.LoadJobConfig(
        schema=[
            bigquery.SchemaField("Id", "INT64"),
            bigquery.SchemaField("SepalLengthCm", "FLOAT64"),
            bigquery.SchemaField("SepalWidthCm", "FLOAT64"),
            bigquery.SchemaField("PetalLengthCm", "FLOAT64"),
            bigquery.SchemaField("PetalWidthCm", "FLOAT64"),
            bigquery.SchemaField("Species", "STRING"),
        ],
        skip_leading_rows=1,
        # The source format defaults to CSV, so the line below is optional.
        source_format=bigquery.SourceFormat.CSV,
    )
    # Define where to download the csv file
    uri = f"gs://{bucket_name}/{file_name}"

    ######################
    # Make an API request to create the table from csv file that are stored in the storage bucket
    load_job = client.load_table_from_uri(
        uri, table_id, job_config=job_config
    )

    load_job.result()  # Waits for the job to complete.

    ######################
    # Make an API request to query the table with table_id
    destination_table = client.get_table(table_id)
    print("Loaded {} rows.".format(destination_table.num_rows))

    return "Event Received"


if __name__ == "__main__":
    app.run(debug=True, host="0.0.0.0", port=int(os.environ.get("PORT", 8080)))
