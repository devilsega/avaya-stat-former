# avaya-stat-former
Parser of local-hosted .csv files generated by AVAYA IP-Office 500 station (Delta server).
Scans the files (the path is set in the .properties) by the http REST request, gives json as an answer.
Listens to this address:
/api/stat/1.0/get
with requested params: String number, String heading (in/out), Date date (pattern="yyyy-MM-dd").
