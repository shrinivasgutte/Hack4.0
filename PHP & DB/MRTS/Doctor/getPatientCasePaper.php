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
    "casepaper.Description,\n".
    "casepaper.Type,\n".
    "casepaper.DoctorID,\n".
    "casepaper.PatientID,\n".
    "casepaper.HospitalID,\n".
    "casepaper.Pic,\n".
    "casepaper.Date,\n".
    "hospital.`Name`,\n".
    "users.FirstName,\n".
    "users.MiddleName,\n".
    "users.LastName\n".
    "FROM\n".
    "casepaper\n".
    "INNER JOIN doctor ON casepaper.DoctorID = doctor.DoctorID\n".
    "INNER JOIN users ON doctor.DoctorID = users.ID\n".
    "INNER JOIN hospital ON casepaper.HospitalID = hospital.HospitalID\n".
    "WHERE casepaper.PatientID=".$id;



$res = mysqli_query($conn,$sql);
$result = array();

while($row = mysqli_fetch_array($res)){
        array_push($result,
            array('Description'=>$row[0],
                'Type'=>$row[1],
                'DoctorID'=>$row[2],
                'HospitalID'=>$row[4],
                'Pic'=>$row[5],
                'Date'=>$row[6],
                'HospitalName'=>$row[7],
                'Name'=>$row[8].' '.$row[9].' '.$row[10],
            ));
}

echo json_encode(array("result"=>$result));