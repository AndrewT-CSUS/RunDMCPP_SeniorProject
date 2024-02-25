import i18n from "i18next";
import { useTranslation, initReactI18next } from "react-i18next";
import en from '../locale/en.json'
import kr from '../locale/kr.json'

i18n.use(initReactI18next).init({
 resources: {
    en: { ...en}, 
    kr: { ...kr },
 }, // Where we're gonna put translations' files
 lng: "kr",     // Set the initial language of the App
});
