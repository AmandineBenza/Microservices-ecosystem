package com.lama.mse.coursiers.accounts.kafka;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import com.lama.mse.coursiers.accounts.model.Coursier;
import com.lama.mse.coursiers.accounts.service.ICoursierService;

@Component
public class CoursierKafkaListener {

	@Autowired
	private ICoursierService coursierService;

	@Autowired
	private KafkaIO kafkaIO;

	public CoursierKafkaListener() {
	}

	@KafkaListener(topics = "create-coursier", topicPartitions = {
			@TopicPartition(topic = "create-coursier", partitions = { "0" }) })
	public void createCoursier(String coursier, Acknowledgment acknowledgment) {
		try {
			coursierService.addCoursier(new Gson().fromJson(coursier, Coursier.class));
		} catch (Exception e) {
			// prevent crash in case user gives bad json
		}
		kafkaIO.sendCreationCoursierMessage(coursier);
		acknowledgment.acknowledge();
	}

	@KafkaListener(topics = "consult-coursier", topicPartitions = {
			@TopicPartition(topic = "consult-coursier", partitions = { "0" }) })
	public void consultCoursier(String email, Acknowledgment acknowledgment) {
		Gson gson = new Gson();
		Coursier coursierToConsult = coursierService.findByEmail(email);
		String coursierJson = coursierToConsult != null ? gson.toJson(coursierToConsult) : null;
		kafkaIO.sendConsultedCoursierMessage(coursierJson);
		acknowledgment.acknowledge();
	}

	@KafkaListener(topics = "edit-coursier-mail", topicPartitions = {
			@TopicPartition(topic = "edit-coursier-email", partitions = { "0" }) })
	public void modifyEmailCoursier(String email, Acknowledgment acknowledgment) {
		String[] split = email.split(";");
		String sentMessageContent = null;

		if (split.length == 2) {
			Coursier coursier = coursierService.findByEmail(split[0].trim());
			if (coursier != null)
				coursier.setMail(split[1].trim());
			sentMessageContent = new Gson().toJson(coursier);
		}

		kafkaIO.sendModifiedEmailMessage(sentMessageContent);
		acknowledgment.acknowledge();
	}

	@KafkaListener(topics = "edit-coursier-name", topicPartitions = {
			@TopicPartition(topic = "edit-coursier-name", partitions = { "0" }) })
	public void modifyNameCoursier(String name, Acknowledgment acknowledgment) {
		String[] split = name.split(";");
		String sentMessageContent = null;

		if (split.length == 2) {
			Coursier coursier = coursierService.findByEmail(split[0].trim());
			if (coursier != null)
				coursier.setName(split[1].trim());
			sentMessageContent = new Gson().toJson(coursier);
		}
		kafkaIO.sendModifiedNameMessage(sentMessageContent);
		acknowledgment.acknowledge();
	}

	@KafkaListener(topics = "edit-coursier-phone", topicPartitions = {
			@TopicPartition(topic = "edit-coursier-phone", partitions = { "0" }) })
	public void modifyPhoneCoursier(String phone, Acknowledgment acknowledgment) {
		String[] split = phone.split(";");
		String sentMessageContent = null;

		if (split.length == 2) {
			Coursier coursier = coursierService.findByEmail(split[0].trim());
			if (coursier != null)
				coursier.setPhone(Integer.parseInt(split[1].trim()));
			sentMessageContent = new Gson().toJson(coursier);
		}
		kafkaIO.sendModifiedPhoneNumberMessage(sentMessageContent);
		acknowledgment.acknowledge();

	}

	@KafkaListener(topics = "edit-coursier-location", topicPartitions = {
			@TopicPartition(topic = "edit-coursier-location", partitions = { "0" }) })
	public void modifyLocatinCoursier(String location, Acknowledgment acknowledgment) {
		String[] split = location.split(";");
		String sentMessageContent = null;

		if (split.length == 2) {
			Coursier coursier = coursierService.findByEmail(split[0].trim());
			if (coursier != null)
				coursier.setLocation(split[1].trim());
			sentMessageContent = new Gson().toJson(coursier);
		}
		kafkaIO.sendModifiedLocationMessage(sentMessageContent);
		acknowledgment.acknowledge();

	}
}