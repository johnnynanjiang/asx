package au.com.jnj.asx.data.repository

import au.com.jnj.asx.data.model.company.Company

interface CompaniesRepository {
    fun getCompanyList(): List<Company>
}

class CompaniesRepositoryImpl : CompaniesRepository {
    override fun getCompanyList(): List<Company> =
            listOf(
                    Company("Nasdaq Index", "NDQ"),
                    Company("realestate.com.au", "REA"),
                    Company("CIM pty ltd", "CIM")
            )
}