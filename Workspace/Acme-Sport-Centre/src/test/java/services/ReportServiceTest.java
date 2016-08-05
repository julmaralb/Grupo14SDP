package services;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Court;
import domain.Report;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class ReportServiceTest extends AbstractTest {

	// Service under test -----------------------------------------------------

	@Autowired
	private ReportService reportService;

	// Other services ---------------------------------------------------------
	
	@Autowired
	private SupervisorService supervisorService;
	
	@Autowired
	private CourtService courtService;

	// Tests ------------------------------------------------------------------
	
	/**
	 * Acme-Sport-Centre - 13.2
	 * A user who is authenticated as a supervisor
	 * must be able to: Manage reports. That includes creating, listing ,
	 * modifying or deleting them.
	 * 
	 * Positive test case: A new report is created.
	 * 
	 */

	@Test
	public void testReportCreate1() {
		Collection<Report> reportsBefore;
		Collection<Report> reportsAfter;
		Report newReport;
		Date today;
		Court court;

		authenticate("supervisor1");

		reportsBefore = reportService.findAll();
		today = new Date();
		court = courtService.findOne(6);

		newReport = reportService.create();
		newReport.setTitle("Nuevo report");
		newReport.setDescription("descripcion del report");
		newReport.setMoment(today);
		newReport.setSupervisor(supervisorService.findByPrincipal());
		newReport.setCourt(court);
		reportService.save(newReport);

		reportsAfter = reportService.findAll();
		Assert.isTrue(reportsAfter.size() == reportsBefore.size()+1);

		authenticate(null);
	}
	
	/**
	 * Acme-Sport-Centre - 13.2
	 * A user who is authenticated as a supervisor
	 * must be able to: Manage reports. That includes creating, listing ,
	 * modifying or deleting them.
	 * 
	 * Negative test case: A new report is not created because it wasn´t a
	 * supervisor
	 */

	@Test(expected = IllegalArgumentException.class)
	public void testReportCreate2() {
		Collection<Report> reportsBefore;
		Collection<Report> reportsAfter;
		Report newReport;
		Date today;
		Court court;

		authenticate("admin");

		reportsBefore = reportService.findAll();
		today = new Date();
		court = courtService.findOne(6);

		newReport = reportService.create();
		newReport.setTitle("Nuevo report");
		newReport.setDescription("descripcion del report");
		newReport.setMoment(today);
		newReport.setSupervisor(supervisorService.findByPrincipal());
		newReport.setCourt(court);
		reportService.save(newReport);

		reportsAfter = reportService.findAll();
		Assert.isTrue(reportsAfter.size() == reportsBefore.size()+1);

		authenticate(null);
	}
	
	/**
	 * Acme-Sport-Centre - 13.2
	 * A user who is authenticated as a supervisor
	 * must be able to: Manage reports. That includes creating, listing ,
	 * modifying or deleting them.
	 * 
	 * Negative test case: A new report is not deleted because it wasn´t a
	 * supervisor
	 */

	@Test(expected = IllegalArgumentException.class)
	public void testCentreCreate2() {
		Report report;

		authenticate("admin");

		report = reportService.findOne(6);

		reportService.delete(report);

		authenticate(null);
	}
}