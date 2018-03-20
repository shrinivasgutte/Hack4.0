<?php

include '../db.php';

if(isset($_GET['id']))
{
    $id=$_GET['id'];
}
else
{
    die("No Data");
}

$sql="SELECT\n".
    "users.ID,\n".
    "users.FirstName,\n".
    "users.MiddleName,\n".
    "users.LastName,\n".
    "users.Address,\n".
    "users.ContactNo,\n".
    "users.Gender,\n".
    "users.DOB,\n".
    "users.BloodGrp,\n".
    "users.UserName,\n".
    "users.Email,\n".
    "users.AadharNo,\n".
    "users.State,\n".
    "users.City,\n".
    "patient.Allergies,\n".
    "users.NfcID\n".
    "FROM\n".
    "users\n".
    "INNER JOIN patient ON users.ID = patient.ID WHERE users.ID=".$id;



$res = mysqli_query($conn,$sql);
$result = array();

while($row = mysqli_fetch_array($res)){
    array_push($result,
        array('id'=>$row[0],
            'Name'=>$row[1].' '.$row[3].' '.$row[2],
            'Address'=>$row[4],
            'ContactNo'=>$row[5],
            'Gender'=>$row[6],
            'DOB'=>$row[7],
            'BloodGrp'=>$row[8],
            'UserName'=>$row[9],
            'Email'=>$row[10],
            'AadharNo'=>$row[11],
            'State'=>$row[12],
            'City'=>$row[13],
            'Allergies'=>$row[14],
            'NFCID'=>$row[15],
        ));
}

echo json_encode(array("result"=>$result));
