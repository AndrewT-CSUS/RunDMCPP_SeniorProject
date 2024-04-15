import { useState } from "react";
import { useTranslation } from "react-i18next";

//Translate Button
export function Translate() {
    const { t, i18n: {changeLanguage, language} } = useTranslation();
    const [currentLanguage, setCurrentLanguage] = useState(language) 
    const handleChangeLanguage = () => {
        const newLanguage = currentLanguage === "en" ? "kr" : "en";
        setCurrentLanguage(newLanguage);
        changeLanguage(newLanguage);
    } 

    return (  
        <button type="button" className="change-language-button" onClick={handleChangeLanguage}>
            Change Language
        </button>
    );
}