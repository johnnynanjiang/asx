package au.com.jnj.asx.domain.sector

/**
 * Created by nanjiang on 16/12/18.
 */

const val NOT_APPLICABLE = "N/A"

enum class SectorDefinitions(val url: String, val code: String = NOT_APPLICABLE, val description: String = NOT_APPLICABLE) {
    ORDINARIES_GOLD(
            "https://au.spindices.com/indices/equity/all-ordinaries-gold-sub-industry",
            "XGD",
            "All Ordinaries Gold"),
    A_REIT(
            "https://au.spindices.com/indices/equity/sp-asx-200-a-reit-sector",
            "XPJ",
            "A-REIT"),
    CONSUMER_DISCRETIONARY(
            "https://au.spindices.com/indices/equity/sp-asx-200-consumer-discretionary-sector",
            "XDJ",
            "Consumer Discretionary"),
    CONSUMER_STAPLES(
            "https://au.spindices.com/indices/equity/sp-asx-200-consumer-staples-sector",
            "XSJ",
            "Consumer Staples"),
    ENERGY(
            "https://au.spindices.com/indices/equity/sp-asx-200-energy-sector",
            "XEJ",
            "Energy"),
    FINANCIAL(
            "https://au.spindices.com/indices/equity/sp-asx-200-financials-sector",
            "XFJ",
            "Financial"),
    FINANCIAL_EX_A_REIT(
            "https://au.spindices.com/indices/equity/sp-asx-200-financial-x-a-reit-sector",
            "XFJ",
            "Financials excluding A-REITs"),
    HEALTH_CARE(
            "https://au.spindices.com/indices/equity/sp-asx-200-health-care-sector",
            "XHJ",
            "Health Care"),
    INDUSTRIALS(
            "https://au.spindices.com/indices/equity/sp-asx-200-industrials-sector",
            "XNJ",
            "Industrials"),
    INFORMATION_TECHNOLOGY(
            "https://au.spindices.com/indices/equity/sp-asx-200-information-technology-sector",
            "XIJ",
            "Information Technology"),
    MATERIALS(
            "https://au.spindices.com/indices/equity/sp-asx-200-materials-sector",
            "XMJ",
            "Materials"),
    METALS_AND_MINING(
            "https://us.spindices.com/indices/equity/sp-asx-300-metals-mining-industry",
            "XMM",
            "Metals and Mining"),
    RESOURCES(
            "https://au.spindices.com/indices/equity/sp-asx-200-resources",
            "XJR",
            "Resources"),
    COMMUNICATION_SERVICES(
            "https://us.spindices.com/indices/equity/sp-asx-200-communication-services-sector",
            "XTJ",
            "Communication Services"),
    UTILITIES(
            "https://us.spindices.com/indices/equity/sp-asx-200-utilities-sector",
            "XUJ",
            "Utilities")
}