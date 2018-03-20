<?php

include '../db.php';
 
   $url=basename($_SERVER['REQUEST_URI']);
$parts=parse_url($url);
 	parse_str($parts['query'], $query);
	
	$p_id=$query['p_id']; // Fetching URL Parameter
	
	
  /*  

	,hospital.Name,hospital.Address,hospital.ContactNo                                                                                 */

	$sql = "SELECT
familymember.Type,
familymember.MemberID,
users.LastName,
users.FirstName,
users.MiddleName,
users.ContactNo
FROM
familymember
INNER JOIN users ON familymember.MemberID = users.ID
 where familymember.PrimaryID='$p_id' and familymember.Accepted='Panding'";

	//$sql = "SELECT casepaper.*, users.LastName,users.MiddleName,users.Address,users.ContactNo,users.Email ,hospital.Name,hospital.Address,hospital.ContactNo FROM casepaper, users , hospital where casepaper.PatientID='11223344' and  users.ID=casepaper.DoctorID and hospital.HospitalID=casepaper.HospitalID;";
	
	$res = mysqli_query($conn,$sql);
 
$result = array();
 
while($row = mysqli_fetch_array($res)){
array_push($result,
array('Relation_Type'=>$row[0],
'MemberID'=>$row[1],
'm_Last_name'=>$row[2],
'm_first_name'=>$row[3],
'm_mid_name'=>$row[4],

'm_ContactNo'=>$row[5]

));
}
 
echo json_encode(array("result"=>$result));
 
mysqli_close($conn);
 
?>