package Wub;

public class TextVar {

   static class Weapon extends TextVar{
        static String weapon_description_1 = "Самый обычный топор. \nНаносит {0} урона.";
        static String weapon_name_1 = "Топор";
        static String weapon_name_2 = "Меч";
        static String weapon_description_2 = "Самый обычный меч. \nНаносит {0} урона.";

        static String weapon_name_3 = "Челюсти";
   }



   static class Armor extends TextVar{
       static String armor_name_1 = "Шкура";
       static String armor_name_2 = "Кожаная броня";
       static String armor_description_2 = "Кожанная броня. \nБлокирует 6 единиц урона.";
   }

   static class Consumnables extends TextVar{
       static String con_name_1 = "Ягодка";
       static String con_description_1 = "Имбовая ягодка. \nВосполняет 200 единиц здоровья.";
   }

   static class LocationsText extends TextVar{
       static String berry_gained = "\nВы полутали {0}.";

       static String obtaining_shop = "'\nВы нашли гамазин!";
       static String gotoshop_button = "В гамазин";
       static String start_button = "Старт";
       static String cave_on_enter = "Вы в пещере. Тут мокро и воняет.";
       static String gotoforest_button = "Вернуться в лес";
       static String forest_on_enter = "Вы в лесу. Тут лесяво.aisfhakjsfhlashflashflashflkhaslkfhkasfhlkashflkashflahsklfhalkshflkashflkhaslfkhasklfhalkshflkashflkahsklfhasklfhfalkshflkahflkash";
       static String gotocave_button = "В пещеры";
       static String gotocity_button = "В город";
       static String obtaining_caves = "\nВы нашли пещеры!";
       static String obtaining_city = "\nВы нашли город!";
       static String city_on_enter = "Вы в городе. Тут всякое";
       static String explore_button = "Исследовать окрестности";
       static String inv_button = "Инвентарь";
       static String nothing = "\nНихуяшеньки";

   }

   static class InventoryText extends TextVar{
       static String inv_use_button = "Использовать";
       static String inv_use_button_1 = "Экипировать";
       static String back_button = "Назад";
       static String weapon = "Оружие:";
       static String armor = "Броня:";
       static String buy_button = "Купить";
       static String money_label = "Голды: {0}";
   }

   static class Battle extends TextVar{
       static String attack_button = "Атаковать";
       static String enemy_deal_damage = "\nВраг нанес Вам {0} единиц урона.";
       static String enemy_detected = "Вы видите врага.";
       static String player_deal_damage = "\nВы нанесли {0} единиц урона.";
       static String enemy_died = "\nВраг всё =(";
       static String player_missed = "\nПромазал, лошара.";
       static String you_died = "\nВы всё.";
       static String enemy_missed = "\nВраг промахнулся.";
       static String victory_button = "Победка";
       static String try_again_button = "Контрпобедка";
       static String loot = "\nВы полутали {0} и {1} голды.";
   }


}
