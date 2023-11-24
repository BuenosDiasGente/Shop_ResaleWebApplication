package ru.skypro.homework.service.impl;

//
//@Service
//@RequiredArgsConstructor
//public class UsersServiceImpl implements UserService {
//
//    private final UserRepository usersRepository;
//    private final UserMapper usersMapper;
//
//    @Override
//    public boolean updatePassword(NewPasswordDTO password) {
//        return false;
//    }
//
//    @Override
//    public UserDTO getUser() {
//        //Если, аутентификация прошла успешно — это значит, имя и пароль верные.
//        //Тогда объект Authentication сохраняется в SecurityContext, а тот, в свою очередь, — в SecurityContextHolder:
//        //Authentication-объект, отражающий информацию о текущем пользователе и его привилегиях.
//        //getPrincipal()-метод получения текущего пользователя
//        //После  успешной аутентификации в поле Principal объекта Authentication будет реальный пользователь в виде UserDetails:
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String username = ((UserDetails) authentication.getPrincipal()).getUsername();
//        User user = usersRepository.findUserByUserName(username);
//        return usersMapper.usersEntityToUsersDto(user);
//    }
//
//    @Override
//    public UpdateUserDTO updateUser(UpdateUserDTO updateUserDTO) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String username = ((UserDetails) authentication.getPrincipal()).getUsername();
//        User user = usersRepository.findUserByUserName(username);
//        user.setName(updateUserDTO.getName());
//        user.setSurname(updateUserDTO.getSurname());
//        user.setPhone(updateUserDTO.getPhone());
//        usersRepository.save(user);
//        return usersMapper.userEntityToUpdateUsersDto(user);
//    }

//    @Override
//    public void updateUserImage(MultipartFile image){
//    }


//    private User findUserByLogin(Authentication authentication) {
//        // SecurityContextHolder.getContext().getAuthentication();
//        User user = usersRepository.findUserByUserName(authentication.getName());
//        if (userLogin.isEmpty()) {
//            throw new NotFoundConfigException();
//        } else {
//          return userLogin.get();
//        }
//    }
//}
