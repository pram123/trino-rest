from flask import Flask

app = Flask(__name__)


@app.route('/crm/companies')
def index():
    return '{"status_code":200,"status":"OK","service":"zoho-crm","resource":"companies","operation":"all","data":[{"id":"12345","name":"SpaceX"}],"meta":{"items_on_page":1,"cursors":{"previous":"em9oby1jcm06OnBhZ2U6OjE=","current":"em9oby1jcm06OnBhZ2U6OjI=","next":""}},"links":{"previous":"https://unify.apideck.com/crm/companies?cursor=em9oby1jcm06OnBhZ2U6OjE%3D","current":"https://unify.apideck.com/crm/companies","next":null}}'


if __name__ == '__main__':
    app.run(debug=True)
