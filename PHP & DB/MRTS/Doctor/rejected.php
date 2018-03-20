<?php

include '../db.php';
 
   $url=basename($_SERVER['REQUEST_URI']);
$parts=parse_url($url);
 	parse_str($parts['query'], $query);
	
	$p_id=$query['p_id']; // Fetching URL Parameter
	
	

	$sql = "SELECT
request.RequestID,
request.PatientID,
request.DoctorID,
request.Description,
request.ColumnName,
request.ColumnValue,
request.Type,
request.Accept
FROM
request
WHERE
request.Accept = 'Rejected' and DoctorID='$p_id'";

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
'Type'=>$row[7]
));
}
 
echo json_encode(array("result"=>$result));
 
mysqli_close($conn);
 
?>