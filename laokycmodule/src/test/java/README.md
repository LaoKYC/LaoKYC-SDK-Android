# Lao KYC Android OIDC Test Cases
 ຄູ່ມືໃນການພັດທະນາ App ເພື່ອ Auth ຜ່ານ LaoKYC
 ## ລາຍການຄຳອາທິບາຍ ລະຫັດ
 ລຳດັບ | ຕົວຫຍໍ້ | ຄຳອາທິບາຍ
----- | ----- | ----------
1 | MD | Module
2 | NC | Normal Case
3 | AC | Alternate Case
4 | EC | Exception Case
5 | P | Process
## ລາຍການຄຳອາທີບາຍ ລະຫັດ Module
ລຳດັບ | ຕົວຫຍໍ້ | ຄຳອາທິບາຍ
----- | ----- | ----------
1 | SSO | Single SignOn
2 | VLT | Validator
## Single SignOn
ແມ່ນ Module ທີ່ໄວ້ຈັດການກັບຂໍ້ມູນ ໃນສ່ວນຂອງ Single SignOn
ລຳດັບ | ຕົວຫຍໍ້ | ຄຳອາທິບາຍ | Implement | Production
----- | ----- | ---------- | --------- | --
1 | MD-SSO-P1-NC01 | ສົ່ງ Request ໄປຫາ API ເພື່ອ Verify state | No | No
2 | MD-SSO-P1-NC02 | ສົ່ງ OTP Request ໄປຫາ API ຂໍ Login | No | No
3 | MD-SSO-P2-NC03 | ດຶງຂໍ້ມູນຈາກ Claims | No | No

## Validator Services
ແມ່ນ Module ທີ່ໄວ້ຈັດການກັບຂໍ້ມູນ ໃນສ່ວນຂອງ Validator Services
ລຳດັບ | ຕົວຫຍໍ້ | ຄຳອາທິບາຍ | Implement | Production
----- | ----- | ---------- | --------- | --
1 | MD-VLT-P1-NC01 | ສົ່ງ Request ໄປຫາ API ເພື່ອ Verify simreg status | No | No
