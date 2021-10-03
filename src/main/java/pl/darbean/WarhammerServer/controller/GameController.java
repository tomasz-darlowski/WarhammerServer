package pl.darbean.WarhammerServer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pl.darbean.WarhammerServer.model.ImportExportJsonObject;
import pl.darbean.WarhammerServer.model.attributes.Attrib;
import pl.darbean.WarhammerServer.model.attributes.CharacterAttribute;
import pl.darbean.WarhammerServer.model.inventory.ArmoryStaff;
import pl.darbean.WarhammerServer.model.inventory.Weapon;
import pl.darbean.WarhammerServer.model.skills.Skill;

import java.util.HashMap;
import java.util.Map;

@RestController
public class GameController {

    private Logger logger = LoggerFactory.getLogger(GameController.class);


    public final static Map<String, ImportExportJsonObject> sessionHeroes = new HashMap<>();

    @PostMapping(value = "/postCharacterData", consumes = "application/json")
    public @ResponseBody
    String registerCharacter(@RequestBody ImportExportJsonObject heroData) {
        processHeroData(heroData);
        logger.info("Received data for character {}", heroData.getHero().getNameAndSurname());
        return "OK";
    }

    @PostMapping(value = "/tsopCharacterData", consumes = "application/json")
    public @ResponseBody
    String deregisterCharacter(@RequestBody ImportExportJsonObject heroData) {
        sessionHeroes.remove(heroData.getHero().getNameAndSurname());
        logger.info("Character {} go away", heroData.getHero().getNameAndSurname());
        return "OK";
    }

    private void processHeroData(ImportExportJsonObject heroData) {
        Map<CharacterAttribute, Attrib> attributeMap = new HashMap<>();
        for (Attrib attrib : heroData.getAttribs()) {
            attributeMap.put(attrib.getAttribute(), attrib);
        }
        for (Skill basicSkill : heroData.getBasicSkills()) {
            basicSkill.setAdvance(basicSkill.getAdvance() + attributeMap.get(basicSkill.getAttribute()).getTotal());
        }
        for (Skill advSkill : heroData.getAdvancedSkills()) {
            advSkill.setAdvance(advSkill.getAdvance() + attributeMap.get(advSkill.getAttribute()).getTotal());
        }
        for (ArmoryStaff armor : heroData.getArmoryStaffsList()) {
            armor.setArmorPoints(armor.getArmorPoints() + attributeMap.get(CharacterAttribute.WT).getBonus());
        }
        for (Weapon weapon : heroData.getWeapons()) {
            if (weapon.isWithStrengthBonus()) {
                weapon.setDamage(weapon.getDamage(attributeMap.get(CharacterAttribute.S)));
            }
        }
        sessionHeroes.put(heroData.getHero().getNameAndSurname(), heroData);
    }

    public Map<String, ImportExportJsonObject> getMockDB() {
        return sessionHeroes;
    }
}
