<?php

if(isset($_GET['id']))
{
    $id=$_GET['id'];
}
else
{
    die("No Get Data");
}

include '../db.php';

$sql="SELECT\n".
    "reports.ID,\n".
    "reports.DoctorID,\n".
    "reports.LabID,\n".
    "reports.ReportType,\n".
    "reports.ReportName,\n".
    "reports.Description,\n".
    "reports.Pic,\n".
    "reports.Date,\n".
    "labs.LabName,\n".
    "users.FirstName,\n".
    "users.MiddleName,\n".
    "users.LastName\n".
    "FROM\n".
    "reports\n".
    "INNER JOIN labs ON reports.LabID = labs.LabID\n".
    "INNER JOIN users ON reports.DoctorID = users.ID\n".
    "WHERE reports.PatientID=".$id;



$res = mysqli_query($conn,$sql);
$result = array();

while($row = mysqli_fetch_array($res)){
        array_push($result,
            array(
                'ID'=>$row[0],
                'DoctorID'=>$row[1],
                'LabID'=>$row[2],
                'ReportType'=>$row[3],
                'ReportName'=>$row[4],
                'Description'=>$row[5],
                'Pic'=>$row[6],
                'Date'=>$row[7],
                'LabName'=>$row[8],
                'Name'=>$row[9].' '.$row[10].' '.$row[11],
            ));
}

echo json_encode(array("result"=>$result));