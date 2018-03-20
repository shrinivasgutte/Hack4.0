<?php

include '../db.php';
 
   $url=basename($_SERVER['REQUEST_URI']);
$parts=parse_url($url);
 	//parse_str($parts['query'], $query);
	
	//$p_id=$query['p_id']; // Fetching URL Parameter

	$sql = "SELECT
request.RequestID,
request.PatientID,
request.DoctorID,
request.Description,
request.Accept,
request.ColumnName,
request.ColumnValue,
request.Type,
request.PatientIDColumnName
FROM
request
WHERE
request.Accept = 'Pending'";


	$res = mysqli_query($conn,$sql);
 
$result = array();
 
while($row = mysqli_fetch_array($res)){
array_push($result,
array('RequestID'=>$row[0],
'PatientID'=>$row[1],
'DoctorID'=>$row[2],
'Description'=>$row[3],
'Accept'=>$row[4],
'ColumnName'=>$row[5],
'ColumnValue'=>$row[6],
'Type'=>$row[7],
'PatientIDColumnName'=>$row[8]
));
}
 
echo json_encode(array("result"=>$result));
 
mysqli_close($conn);
 
?>