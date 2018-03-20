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
    "medical.`Name`,\n".
    "prescription.DoctorID,\n".
    "prescription.PrescriptionID,\n".
    "prescription.Pic,\n".
    "prescription.Date,\n".
    "prescription.MedicalID,\n".
    "users.LastName,\n".
    "users.FirstName,\n".
    "users.MiddleName\n".
    "FROM\n".
    "prescription\n".
    "INNER JOIN users ON prescription.DoctorID = users.ID\n".
    "INNER JOIN medical ON prescription.MedicalID= medical.MedicalID\n".
    "WHERE prescription.PatientID=".$id;

$res = mysqli_query($conn,$sql);
$result = array();

while($row = mysqli_fetch_array($res)){
        array_push($result,
            array(
                'MedicalName'=>$row[0],
                'DoctorID'=>$row[1],
                'PrescriptionID'=>$row[2],
                'Pic'=>$row[3],
                'Date'=>$row[4],
                'MedicalID'=>$row[5],
                'DoctorName'=>$row[6].' '.$row[7].' '.$row[8],
            ));
}

echo json_encode(array("result"=>$result));