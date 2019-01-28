package au.com.jnj.asx.data.repository

import au.com.jnj.asx.data.model.company.Company

interface CompanyRepository {
    fun getCompany(): Company
}

class CompanyRepositoryImpl : CompanyRepository {
    override fun getCompany(): Company =
            Company("NDQ", "NDQ")
}