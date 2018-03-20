<?php

include '../db.php';

$did=$_GET['did'];

$sql = "SELECT\n".
    "	casepaper.PatientID,\n".
    "	users.FirstName,\n".
    "	users.LastName,\n".
    "	users.MiddleName,\n".
    "	users.ContactNo,\n".
    "	users.UserName,\n".
    "	users.DOB \n".
    "FROM\n".
    "	casepaper\n".
    "	INNER JOIN users ON users.ID = casepaper.PatientID \n".
    "WHERE\n".
    "	casepaper.DoctorID=".$did;

$res = mysqli_query($conn,$sql);

echo $res;
echo $sql;
echo $conn;
$result = array();

/*while($row = mysqli_fetch_array($res)){
    array_push($result,
        array('id'=>$row[0],
            'name'=>$row[1].' '.$row[3].' '.$row[2],
            'mobile'=>$row[4],
            'username'=>$row[5],
            'dob'=>$row[6]
        ));
}
*/
/*echo json_encode(array("result"=>$result));

mysqli_close($conn);*/