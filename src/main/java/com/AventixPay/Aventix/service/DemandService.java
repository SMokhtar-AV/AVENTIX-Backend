package com.AventixPay.Aventix.service;


import com.AventixPay.Aventix.entities.Demand;
import com.AventixPay.Aventix.entities.Notification;
import com.AventixPay.Aventix.entities.Roles;
import com.AventixPay.Aventix.entities.User;
import com.AventixPay.Aventix.enumClass.DemandeEtat;
import com.AventixPay.Aventix.enumClass.NotificationType;
import com.AventixPay.Aventix.enumClass.Role;
import com.AventixPay.Aventix.repositories.DemandRepository;
import com.AventixPay.Aventix.repositories.RoleRepository;
import com.AventixPay.Aventix.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class DemandService {
    @Autowired
    private DemandRepository demandRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    UserService userService;

    @Autowired
    NotificationService notificationService;

    public DemandService() {}

    public Demand createDemand(Demand demand) {
        return demandRepository.save(demand);
    }

    public List<Demand> getAllDemands() {
        return demandRepository.findAll();
    }

    public List<Demand> getAllDemandsNonValidated() {
        return demandRepository.findByEtat(DemandeEtat.NON_VALIDE);
    }

    public Demand getDemandById(Long id) {
        return demandRepository.findById(id).orElseThrow(() -> new RuntimeException("Demand not found"));
    }

    public Demand updateDemand(Long id, Demand demandDetails) {
        Demand demand = getDemandById(id);
        demand.setType(demandDetails.getType());
        demand.setMessage(demandDetails.getMessage());
        demand.setDescription(demandDetails.getDescription());
        demand.setDemandeur(demandDetails.getDemandeur());
        demand.setRecepteur(demandDetails.getRecepteur());
        demand.setNotification(demandDetails.getNotification());
        return demandRepository.save(demand);
    }

    public void deleteDemand(Long id) {
        demandRepository.deleteById(id);
    }

    public Demand createDemandCarte(String email, Demand demandDetails) {

        User employeur = userService.getUserByEmail(email);

        Role roleEnum = Role.valueOf("ADMIN");

        Roles role = roleRepository.findByRole(roleEnum)
                .orElseThrow(() -> new RuntimeException("Role ADMIN non trouvé dans la base de données"));
        User admin = userService.findUserByRole(role);
        demandDetails.setDemandeur(employeur);
        demandDetails.setRecepteur(admin);
        demandDetails.setEtat(DemandeEtat.NON_VALIDE);
        demandDetails.setDate(Timestamp.valueOf(LocalDateTime.now()));
        createDemand(demandDetails);
        String s = "Demande de creation d'un compte par l'employeur" + employeur.getFirstName();
        Notification notification = new Notification(NotificationType.DEMANDE, s);
        demandDetails.setNotification(notification);

        notification.setDemand(demandDetails);
        notificationService.createNotification(notification);
        return demandRepository.save(demandDetails);


    }

}
