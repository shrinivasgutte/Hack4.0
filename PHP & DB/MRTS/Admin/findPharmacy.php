<?php
include '../db.php';
   $url=basename($_SERVER['REQUEST_URI']);
$parts=parse_url($url);
 	parse_str($parts['query'], $query);
	
	$State=$query['State']; // Fetching URL Parameter
	$City=$query['City']; // Fetching URL Parameter
	
 
	$sql = "SELECT
	medical.`Name`,
medical.MedicalID,
medical.ContactNo,
medical.Address,
medical.WorkerID
FROM
medical WHERE State='$State'  and  City='$City'";
	
	$res = mysqli_query($conn,$sql);
 
$result = array();
 
while($row = mysqli_fetch_array($res)){
array_push($result,
array('Name'=>$row[0],
'MedicalID'=>$row[1],
'ContactNo'=>$row[2],
'Address'=>$row[3],
'WorkerID'=>$row[4]
));
}
 //'HospitalID'=>$row[3],
echo json_encode(array("result"=>$result));
 
mysqli_close($conn);
 
?>