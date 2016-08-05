package services;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ReportRepository;
import domain.Report;
import domain.Supervisor;

@Service
@Transactional
public class ReportService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private ReportRepository reportRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private SupervisorService supervisorService;
	
	@Autowired
	private ActorService actorService;

	// Constructors -----------------------------------------------------------

	public ReportService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Report create() {
		Assert.isTrue(actorService.checkAuthority("SUPERVISOR"));
		Report result;
		Supervisor principal;
		Date moment;

		result = new Report();
		principal = supervisorService.findByPrincipal();
		moment = new Date();

		result.setSupervisor(principal);
		result.setMoment(moment);

		return result;
	}

	public Report findOne(int reportId) {
		Assert.notNull(reportId);

		Report result;

		result = reportRepository.findOne(reportId);

		return result;
	}

	public Collection<Report> findAll() {

		Collection<Report> result;

		result = reportRepository.findAll();

		return result;
	}

	public void save(Report report) {
		Assert.notNull(report);
		long milliseconds;
		Supervisor principal;

		principal = supervisorService.findByPrincipal();
		milliseconds = System.currentTimeMillis() - 100;
		report.setMoment(new Date(milliseconds));
		report.setSupervisor(principal);

		reportRepository.save(report);

	}

	public void delete(Report report) {
		Assert.notNull(report);
		Assert.isTrue(actorService.checkAuthority("SUPERVISOR"));

		reportRepository.delete(report);
	}

	// Other business methods -------------------------------------------------
}